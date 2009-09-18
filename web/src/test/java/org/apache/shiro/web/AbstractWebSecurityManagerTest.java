/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shiro.web;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.subject.support.WebSubjectThreadState;
import org.junit.After;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @since 1.0
 */
public abstract class AbstractWebSecurityManagerTest {

    @After
    public void tearDown() {
        ThreadContext.clear();
    }

    protected Subject newSubject(SecurityManager sm, ServletRequest request, ServletResponse response) {
        ThreadContext.bind(sm);
        WebUtils.bind(request);
        WebUtils.bind(response);
        WebSubject subject = new WebSubject.Builder(sm, request, response).buildWebSubject();
        WebSubjectThreadState threadState = new WebSubjectThreadState(subject);
        threadState.bind();
        return subject;
    }

}