/*
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

package com.google.code.guice.repository.testing.repo;

import com.google.code.guice.repository.BatchStoreJpaRepository;
import com.google.code.guice.repository.EntityManagerProvider;
import com.google.code.guice.repository.testing.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaSpecificationExecutor<User>,
        BatchStoreJpaRepository<User, Long>, EntityManagerProvider, UserRepositoryCustom{

    User findUserByName(String name);

    @Modifying
    @Transactional
    @Query("delete from User u where u.age >= 200")
    void deleteInactiveUsers();

    @Modifying
    @Transactional
    @Query("delete from User u where u.age >= 1")
    void deleteOtherUsers();
}