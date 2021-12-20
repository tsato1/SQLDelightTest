package com.tsato.mobile.sqldelighttest.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.tsato.mobile.sqldelighttest.TestDatabase
import com.tsato.mobile.sqldelighttest.data.*
import com.tsato.mobile.sqldelighttest.domain.CategoryRepository
import com.tsato.mobile.sqldelighttest.domain.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        app: Application,
        providerItemDao: Provider<ItemDao>
    ) : AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .addCallback(
                object: RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        GlobalScope.launch {
                            providerItemDao.get().insertItem(
                                Item(BigDecimal(11), "asdf", "1999-01-01")
                            )
                        }
                    }
                }
            )
            .addMigrations(
                AppDatabase.MIGRATION_1_2
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = TestDatabase.Schema,
            context = app,
            name = "test.db"
        )
    }

    @Provides
    @Singleton
    fun providesItemDataSource(driver: SqlDriver): ItemDataSource {
        return ItemDataSourceImpl(TestDatabase(driver))
    }

    @Provides
    @Singleton
    fun providesItemDao(db: AppDatabase) = db.itemDao

    @Provides
    @Singleton
    fun providesItemRepository(itemDataSource: ItemDataSource): ItemRepository {
//        return ItemRepositoryImpl(db.itemDao)
        return ItemRepositoryImpl(itemDataSource)
    }

    @Provides
    @Singleton
    fun providesCategoryDao(db: AppDatabase) = db.categoryDao

    @Provides
    @Singleton
    fun providesCategoryRepository(db: AppDatabase): CategoryRepository {
        return CategoryRepositoryImpl(db.categoryDao)
    }

}