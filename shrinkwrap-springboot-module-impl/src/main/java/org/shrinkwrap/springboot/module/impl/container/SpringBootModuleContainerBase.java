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
package org.shrinkwrap.springboot.module.impl.container;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.exporter.ZipStoredExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.Validate;
import org.jboss.shrinkwrap.impl.base.container.ContainerBase;
import org.shrinkwrap.springboot.module.api.container.SpringBootModuleContainer;

public abstract class SpringBootModuleContainerBase <T extends Archive<T>> extends ContainerBase<T> implements SpringBootModuleContainer<T> {

    protected SpringBootModuleContainerBase(Class<T> actualType, Archive<?> archive) {
        super(actualType, archive);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.jboss.shrinkwrap.api.container.LibraryContainer#addAsLibrary(Archive)
     */
    @Override
    public T addAsLibrary(final Archive<?> archive) throws IllegalArgumentException {
        Validate.notNull(archive, "Archive must be specified");

        // Libraries are JARs, so add as ZIP
        return add(archive, getLibraryPath(), ZipStoredExporter.class);
    }


    /*
     * (non-Javadoc)
     *
     * @see org.jboss.shrinkwrap.api.container.ManifestContainer#addServiceProvider(java.lang.Class,
     * java.lang.Class<?>[])
     */
    @Override
    public T addAsServiceProvider(Class<?> serviceInterface, Class<?>... serviceImpls) throws IllegalArgumentException {
        asJAR().addAsServiceProvider(serviceInterface, serviceImpls);
        return covarientReturn();
    }

    /* (non-Javadoc)
     * @see org.jboss.shrinkwrap.impl.base.container.ContainerBase#addAsServiceProvider(java.lang.String, java.lang.String[])
     */
    @Override
    public T addAsServiceProvider(String serviceInterface, String... serviceImpls) throws IllegalArgumentException
    {
        asJAR().addAsServiceProvider(serviceInterface, serviceImpls);
        return covarientReturn();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.shrinkwrap.api.container.ServiceProviderContainer#addServiceProviderAndClasses(java.lang.Class,
     * java.lang.Class<?>[])
     */
    @Override
    public T addAsServiceProviderAndClasses(Class<?> serviceInterface, Class<?>... serviceImpls)
        throws IllegalArgumentException {
        asJAR().addAsServiceProvider(serviceInterface, serviceImpls);
        return covarientReturn();
    }

    private JavaArchive asJAR() {
        return as(JavaArchive.class);
    }
}
