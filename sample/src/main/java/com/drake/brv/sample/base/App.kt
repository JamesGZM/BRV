/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drake.brv.sample.base

import android.app.Application
import com.drake.brv.PageRefreshLayout
import com.drake.brv.sample.BR
import com.drake.brv.sample.BuildConfig
import com.drake.brv.sample.R
import com.drake.brv.sample.component.net.SerializationConverter
import com.drake.brv.sample.constants.Api
import com.drake.brv.sample.mock.MockDispatcher
import com.drake.brv.utils.BRV
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.interceptor.RetryInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.trustSSLCertificate
import com.drake.statelayout.StateConfig
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        // 初始化BindingAdapter的默认绑定ID
        BRV.modelId = BR.m

        // 禁止错误缺省页启用下拉刷新
        PageRefreshLayout.refreshEnableWhenError = false

        /**
         *  推荐在Application中进行全局配置缺省页, 当然同样每个页面可以单独指定缺省页.
         *  具体查看 https://github.com/liangjingkanji/StateLayout
         */
        StateConfig.apply {
            emptyLayout = R.layout.layout_empty
            errorLayout = R.layout.layout_error
            loadingLayout = R.layout.layout_loading

            setRetryIds(R.id.msg, R.id.iv)

            onLoading {
                // 此生命周期可以拿到LoadingLayout创建的视图对象, 可以进行动画设置或点击事件.
            }
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }

        NetConfig.initialize(Api.HOST, this) {
            // 数据转换器
            setConverter(SerializationConverter())
            setDebug(BuildConfig.DEBUG)
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))
        }

        MockDispatcher.initialize()
    }
}