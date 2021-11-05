package com.devdk.whetherinfo.data.repository

import com.devdk.whetherinfo.common.Const
import com.devdk.whetherinfo.data.remote.WhetherAPI
import com.devdk.whetherinfo.data.remote.dto.WhetherDto
import com.devdk.whetherinfo.domain.repository.Repository

class RepositoryImpl(
    val api : WhetherAPI
) : Repository {
    override suspend fun getDetail(country : String): WhetherDto {
        return api.getDetail(Const.API_KEY ,country)
    }

}