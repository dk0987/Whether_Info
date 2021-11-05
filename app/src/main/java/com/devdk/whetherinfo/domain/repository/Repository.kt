package com.devdk.whetherinfo.domain.repository

import com.devdk.whetherinfo.data.remote.dto.WhetherDto

interface Repository {
    suspend fun getDetail(country : String) : WhetherDto
}