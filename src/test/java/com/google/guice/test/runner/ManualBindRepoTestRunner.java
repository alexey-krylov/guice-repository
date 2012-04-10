/**
 * Copyright (C) 2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.guice.test.runner;

import com.google.guice.JpaRepositoryModule;
import com.google.guice.JpaRepositoryProvider;
import com.google.guice.common.GuiceTestRunner;
import com.google.guice.test.AccountRepository;
import com.google.guice.test.CustomerRepository;
import com.google.guice.test.CustomerRepositoryImpl;
import com.google.guice.test.UserRepository;
import org.junit.runners.model.InitializationError;

public class ManualBindRepoTestRunner extends GuiceTestRunner {

    /*===========================================[ CLASS METHODS ]==============*/

    public ManualBindRepoTestRunner(Class<?> classToRun) throws InitializationError {
        super(classToRun, new JpaRepositoryModule("test-h2") {
            @Override
            protected void configureRepositories() {
                bind(UserRepository.class).toProvider(new JpaRepositoryProvider<UserRepository>());
                bind(AccountRepository.class).toProvider(new JpaRepositoryProvider<AccountRepository>());
                bind(CustomerRepository.class).toProvider(new JpaRepositoryProvider<CustomerRepository>(CustomerRepositoryImpl.class));
            }
        });
    }
}