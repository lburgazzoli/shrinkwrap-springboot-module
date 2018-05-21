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

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.shrinkwrap.springboot.module.api.SpringBootModuleLayout;
import org.shrinkwrap.springboot.module.api.spec.SpringBootModuleArchive;
import org.shrinkwrap.springboot.module.impl.SpringBootModuleLayouts;
import org.shrinkwrap.springboot.module.impl.container.SpringBootModuleContainerBase;

public class SpringBootModuleArchiveImpl extends SpringBootModuleContainerBase<SpringBootModuleArchive> implements SpringBootModuleArchive {
    /**
     * Path to the manifests inside of the Archive.
     */
    private static final ArchivePath PATH_MANIFEST = ArchivePaths.create("META-INF");

    /**
     * Path to service providers.
     */
    private static final ArchivePath PATH_SERVICE_PROVIDERS = ArchivePaths.create(PATH_MANIFEST, "services");


    private SpringBootModuleLayout layout = SpringBootModuleLayouts.DEFAULT;

    public SpringBootModuleArchiveImpl(final Archive<?> delegate) {
        super(SpringBootModuleArchive.class, delegate);
    }

    @Override
    public SpringBootModuleArchive setSpringBootModuleLayout(SpringBootModuleLayout layout) {
        this.layout = layout;
        return this;
    }

    @Override
    protected ArchivePath getManifestPath() {
        return PATH_MANIFEST;
    }

    @Override
    protected ArchivePath getResourcePath() {
        return layout.getLibrariesPath();
    }

    @Override
    protected ArchivePath getClassesPath() {
        return PATH_SERVICE_PROVIDERS;
    }

    @Override
    protected ArchivePath getLibraryPath() {
        return layout.getLibrariesPath();
    }
}
