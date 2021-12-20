package com.tsato.mobile.sqldelighttest.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tsato.mobile.sqldelighttest.data.AppDatabase
import com.tsato.mobile.sqldelighttest.data.Item
import com.tsato.mobile.sqldelighttest.data.ItemDao
import com.tsato.mobile.sqldelighttest.data.ItemRepositoryImpl
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
            .build()
    }

    @Provides
    @Singleton
    fun providesItemDao(db: AppDatabase) = db.itemDao

    @Provides
    @Singleton
    fun providesItemRepository(db: AppDatabase): ItemRepository {
        return ItemRepositoryImpl(db.itemDao)
    }

}