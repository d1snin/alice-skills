/*
 * Copyright 2024 Mikhail Titov
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

plugins {
    application
    id("io.ktor.plugin")
}

dependencies {
    val exktVersion: String by project

    val ktorVersion: String by project

    val logbackVersion: String by project
    val kmLogVersion: String by project

    val koinVersion: String by project

    implementation(project(":alice-sys-actions"))

    implementation("dev.d1s.exkt:exkt-common:$exktVersion")
    implementation("dev.d1s.exkt:exkt-ktor-server:$exktVersion")
    implementation("dev.d1s.exkt:exkt-ktor-server-koin:$exktVersion")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.lighthousegames:logging:$kmLogVersion")

    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
}

application {
    mainClass.set("dev.d1s.aliceskills.webhook.MainKt")
}

ktor {
    docker {
        localImageName.set(project.name)
        jreVersion.set(JavaVersion.VERSION_21)
        jib.from.image = "eclipse-temurin:21-jre"
        jib.container.mainClass = "net.mxteam.store.api.MainKt"
    }
}