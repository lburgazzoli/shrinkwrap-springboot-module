/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.shrinkwrap.springboot.module.impl.spec;

import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.shrinkwrap.springboot.module.api.spec.SpringBootModuleArchive;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringBootModuleArchiveTest {

    @Test
    public void testShouldAddLibsInLibDirectory() {
        final SpringBootModuleArchive archive = prepareSpringBootModuleArchive();
        final Node libDirectory = archive.get("/lib");

        assertThat(libDirectory.getChildren())
            .extracting("path")
            .contains(new BasicPath("/lib/shrinkwrap-api-1.2.6.jar"));
        assertThat(libDirectory.getChildren())
            .extracting("path")
            .contains(new BasicPath("/lib/shrinkwrap-impl-base-1.2.6.jar"));
    }

    private static SpringBootModuleArchive prepareSpringBootModuleArchive() {
        return ShrinkWrap.create(SpringBootModuleArchive.class)
            .addAsLibraries(
                Maven.resolver()
                    .resolve("org.jboss.shrinkwrap:shrinkwrap-api:1.2.6")
                    .withTransitivity()
                    .as(JavaArchive.class))
            .addAsLibraries(
                Maven.resolver()
                    .resolve("org.jboss.shrinkwrap:shrinkwrap-impl-base:1.2.6")
                    .withTransitivity()
                    .as(JavaArchive.class));
    }
}
