package com.devdk.whetherinfo.di

import com.devdk.whetherinfo.common.Const
import com.devdk.whetherinfo.data.remote.WhetherAPI
import com.devdk.whetherinfo.data.repository.RepositoryImpl
import com.devdk.whetherinfo.domain.repository.Repository
import com.devdk.whetherinfo.domain.useCases.GetDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideWhetherApi():WhetherAPI{
       return Retrofit.Builder()
           .baseUrl(Const.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(WhetherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WhetherAPI) : Repository{
        return RepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository):GetDetail{
        return GetDetail(repository)
    }
}