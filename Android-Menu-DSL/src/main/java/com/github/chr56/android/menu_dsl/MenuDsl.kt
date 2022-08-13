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
import com.github.chr56.android.menu_extension.add
import com.github.chr56.android.menu_extension.addSubMenu
import com.github.chr56.android.menu_model.MenuRoot
import com.github.chr56.android.menu_model.MenuItemContext
import com.github.chr56.android.menu_model.SubMenuContext

//
// Attach Menu
// Context: AndroidContext
//

@JvmName("attachExt")
inline fun Context.attach(from: Menu, cfg: MenuRoot.() -> Unit) {
    val menuRoot = MenuRoot(from, this)
    menuRoot.apply(cfg)
}

inline fun attach(context: Context, from: Menu, cfg: MenuRoot.() -> Unit) {
    val menuRoot = MenuRoot(from, context)
    menuRoot.apply(cfg)
}


//
// Context: MenuRoot (root)
//


//
// Full Block DSL
//

fun MenuRoot.menuItem(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

// fun MenuRoot.subMenu(cfg: SubMenuContext.() -> Unit): SubMenu =
//    rootMenu.addSubMenu(this, cfg)

inline fun MenuRoot.menuItemInline(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

// inline fun MenuRoot.subMenuInline(cfg: SubMenuContext.() -> Unit): SubMenu =
//    rootMenu.addSubMenu(this, cfg)

//
// Partial Block DSL
//

fun MenuRoot.menuItem(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, groupId, itemId, order, title, cfg)

fun MenuRoot.menuItem(
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, title, cfg)

fun MenuRoot.submenu(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, groupId, itemId, order, title, cfg)

fun MenuRoot.submenu(
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, title, cfg)



//
// Context: SubMenuContext (submenu)
//

//
// Full Block DSL
//

fun SubMenuContext.menuItem(cfg: MenuItemContext.() -> Unit): MenuItem =
    currentMenu.add(menuRoot, cfg)

// fun SubMenuContext.subMenu(cfg: SubMenuContext.() -> Unit): SubMenu =
//    currentMenu.addSubMenu(MenuRoot, cfg)

inline fun SubMenuContext.menuItemInline(cfg: MenuItemContext.() -> Unit): MenuItem =
    currentMenu.add(menuRoot, cfg)

// inline fun SubMenuContext.subMenuInline(cfg: SubMenuContext.() -> Unit): SubMenu =
//    currentMenu.addSubMenu(MenuRoot, cfg)

//
// Partial Block DSL
//
fun SubMenuContext.menuItem(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem =
    currentMenu.add(menuRoot, groupId, itemId, order, title, cfg)

fun SubMenuContext.menuItem(
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem =
    currentMenu.add(menuRoot, title, cfg)

fun SubMenuContext.subMenu(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    currentMenu.addSubMenu(menuRoot, groupId, itemId, order, title, cfg)

fun SubMenuContext.subMenu(
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    currentMenu.addSubMenu(menuRoot, title, cfg)

