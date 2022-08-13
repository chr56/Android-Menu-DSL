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

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu

@JvmName("attachExt")
inline fun Context.attach(from: Menu, cfg: MenuContext.() -> Unit) {
    val menuContext = MenuContext(from, this)
    menuContext.apply(cfg)
}

inline fun attach(context: Context, from: Menu, cfg: MenuContext.() -> Unit) {
    val menuContext = MenuContext(from, context)
    menuContext.apply(cfg)
}

//
// Full Block DSL
//

fun MenuContext.menuItem(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

fun MenuContext.subMenu(cfg: SubMenuItemContext.() -> Unit): SubMenu =
    rootMenu.addSubMenu(this, cfg)

inline fun MenuContext.menuItemInline(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

inline fun MenuContext.subMenuInline(cfg: SubMenuItemContext.() -> Unit): SubMenu =
    rootMenu.addSubMenu(this, cfg)

//
// Partial Block DSL
//

fun MenuContext.menuItem(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, groupId, itemId, order, title, cfg)

fun MenuContext.menuItem(
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, title, cfg)

fun MenuContext.submenu(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: SubMenuItemContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, groupId, itemId, order, title, cfg)

fun MenuContext.submenu(
    title: CharSequence,
    cfg: SubMenuItemContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, title, cfg)
