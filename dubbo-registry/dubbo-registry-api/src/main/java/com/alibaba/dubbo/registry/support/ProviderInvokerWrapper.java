/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.registry.support;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 服务提供者 Invoker Wrapper
 *
 * @date 2017/11/23
 */
public class ProviderInvokerWrapper<T> implements Invoker {

    /**
     * Invoker 对象
     */
    private Invoker<T> invoker;
    /**
     * 原始 URL
     */
    private URL originUrl;
    /**
     * 注册中心 URL
     */
    private URL registryUrl;
    /**
     * 服务提供者 URL
     */
    private URL providerUrl;
    /**
     * 是否注册
     */
    private volatile boolean isReg;

    public ProviderInvokerWrapper(Invoker<T> invoker,URL registryUrl,URL providerUrl) {
        this.invoker = invoker;
        this.originUrl = URL.valueOf(invoker.getUrl().toFullString());// registry://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?application=demo-provider&dubbo=2.0.0&export=dubbo%3A%2F%2F10.115.16.87%3A20880%2Fcom.alibaba.dubbo.demo.DemoService%3Faccesslog%3Dtrue%26anyhost%3Dtrue%26application%3Ddemo-provider%26bind.ip%3D10.115.16.87%26bind.port%3D20880%26callbacks%3D1000%26default.delay%3D-1%26default.proxy%3Djdk%26default.retries%3D0%26delay%3D-1%26deprecated%3Dfalse%26dubbo%3D2.0.0%26generic%3Dfalse%26group%3Dg1%26interface%3Dcom.alibaba.dubbo.demo.DemoService%26logger%3Djcl%26methods%3DcallbackParam%2CsayHello%2Csave%2Cupdate%2Csay03%2Cdelete%2Csay04%2Cdemo%2Csay01%2Cbye%2Csay02%2Chello02%2Csaves%2Chello01%2Chello%26pid%3D36625%26qos.port%3D22222%26say01.deprecated%3Dtrue%26server%3Dnetty4%26service.filter%3Ddemo%26side%3Dprovider%26timeout%3D200000%26timestamp%3D1571814220310&logger=jcl&pid=36625&qos.port=22222&registry=zookeeper&timestamp=1571814210273
        this.registryUrl = URL.valueOf(registryUrl.toFullString());// 注册中心的 URL：zookeeper://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?application=demo-provider&dubbo=2.0.0&export=dubbo%3A%2F%2F10.115.16.87%3A20880%2Fcom.alibaba.dubbo.demo.DemoService%3Faccesslog%3Dtrue%26anyhost%3Dtrue%26application%3Ddemo-provider%26bind.ip%3D10.115.16.87%26bind.port%3D20880%26callbacks%3D1000%26default.delay%3D-1%26default.proxy%3Djdk%26default.retries%3D0%26delay%3D-1%26deprecated%3Dfalse%26dubbo%3D2.0.0%26generic%3Dfalse%26group%3Dg1%26interface%3Dcom.alibaba.dubbo.demo.DemoService%26logger%3Djcl%26methods%3DcallbackParam%2CsayHello%2Csave%2Cupdate%2Csay03%2Cdelete%2Csay04%2Cdemo%2Csay01%2Cbye%2Csay02%2Chello02%2Csaves%2Chello01%2Chello%26pid%3D36625%26qos.port%3D22222%26say01.deprecated%3Dtrue%26server%3Dnetty4%26service.filter%3Ddemo%26side%3Dprovider%26timeout%3D200000%26timestamp%3D1571814220310&logger=jcl&pid=36625&qos.port=22222&timestamp=1571814210273
        this.providerUrl = providerUrl;// 提供者的 URL：dubbo://10.115.16.87:20880/com.alibaba.dubbo.demo.DemoService?accesslog=true&anyhost=true&application=demo-provider&callbacks=1000&default.delay=-1&default.proxy=jdk&default.retries=0&delay=-1&deprecated=false&dubbo=2.0.0&generic=false&group=g1&interface=com.alibaba.dubbo.demo.DemoService&logger=jcl&methods=callbackParam,sayHello,save,update,say03,delete,say04,demo,say01,bye,say02,hello02,saves,hello01,hello&pid=36625&say01.deprecated=true&server=netty4&service.filter=demo&side=provider&timeout=200000&timestamp=1571814220310
    }

    public Class<T> getInterface() {
        return invoker.getInterface();
    }

    public URL getUrl() {
        return invoker.getUrl();
    }

    public boolean isAvailable() {
        return invoker.isAvailable();
    }

    public Result invoke(Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    public void destroy() {
        invoker.destroy();
    }

    public URL getOriginUrl() {
        return originUrl;
    }

    public URL getRegistryUrl() {
        return registryUrl;
    }

    public URL getProviderUrl() {
        return providerUrl;
    }

    public Invoker<T> getInvoker() {
        return invoker;
    }

    public boolean isReg() {
        return isReg;
    }

    public void setReg(boolean reg) {
        isReg = reg;
    }
}
