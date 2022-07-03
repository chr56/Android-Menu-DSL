/*
 * Copyright (C) 2022 chr_56
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("MenuDsl")
package com.github.chr56.android.menu_dsl

import android.view.Menu

inline fun menu(from: Menu, cfg: MenuContext.() -> Unit) {
    val menuContext = MenuContext(from)
    menuContext.apply(cfg)
}

inline fun MenuContext.menuItem(cfg: MenuItemCfg.() -> Unit) {
    menu.add(cfg)
}
