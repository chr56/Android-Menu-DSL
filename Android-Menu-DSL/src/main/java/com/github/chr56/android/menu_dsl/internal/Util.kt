/*
 *  Copyright (C) 2022 chr_56
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.github.chr56.android.menu_dsl

import java.lang.Exception

internal class PropertyMapDelegate : Iterable<Int> {
    internal val map: MutableMap<Int, Any?> = LinkedHashMap()
    operator fun <T> set(key: Int, value: T) {
        map[key] = value
    }
    operator fun <T> get(key: Int): T? {
        val result = map[key] ?: return null
        return try {
            result as T
        } catch (e: Exception) {
            null
        }
    }
    fun export(): MutableMap<Int, Any?> = map

    /**
     * only output keys
     */
    override fun iterator(): Iterator<Int> =
        object : Iterator<Int> {
            val keys = ArrayList(map.keys)
            var currentIndex = 0
            override fun hasNext(): Boolean =
                currentIndex < keys.size

            override fun next(): Int = keys[currentIndex++]
        }
}
