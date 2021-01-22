/*
 * Copyright 2014-2021 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.tests.server.cio

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlin.test.*

class TestConnectorInterfaceUsed {
    @Test
    fun testConnectorListenHost() {
        val engine = CIOApplicationEngine(applicationEngineEnvironment {
            connector {
                host = "some/illegal/host/name\u0007"
                port = 9091
            }
        }) {}

        assertFails {
            engine.start()

            // this shouldn't happen
            engine.stop(1000, 2000)
        }
    }
}
