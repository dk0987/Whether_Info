package com.devdk.whetherinfo.domain.useCases

import com.devdk.whetherinfo.common.Resource
import com.devdk.whetherinfo.data.remote.dto.WhetherDto
import com.devdk.whetherinfo.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDetail(
    private val repository: Repository
) {
     operator fun invoke(country : String) : Flow<Resource<WhetherDto>> = flow{
        try {
            emit(Resource.Loading<WhetherDto>())
            val dto = repository.getDetail(country)
            emit(Resource.Success<WhetherDto>(dto))
        }catch (e : HttpException){
            emit(Resource.Error<WhetherDto>(e.localizedMessage ?: "Check Your Internet Connection " ))
        }
        catch (e : IOException){
            emit(Resource.Error<WhetherDto>("Check Your Internet  Connection"))
        }
    }
}