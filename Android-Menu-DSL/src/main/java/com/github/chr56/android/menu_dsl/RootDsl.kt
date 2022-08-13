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
@file:JvmName("RootDSL")

package com.github.chr56.android.menu_dsl

import android.view.MenuItem
import android.view.SubMenu
import com.github.chr56.android.menu_extension.add
import com.github.chr56.android.menu_extension.addSubMenu
import com.github.chr56.android.menu_model.MenuContext
import com.github.chr56.android.menu_model.MenuItemContext
import com.github.chr56.android.menu_model.SubMenuContext

////////////////////////////////
// Receiver: MenuContext (root)
////////////////////////////////

///////////////////
// Full Block DSL
///////////////////

/**
 * create and return a new [MenuItem] using the [MenuItemContext] DSL block
 *
 * Type: Full Block DSL
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
fun MenuContext.menuItem(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

// fun MenuContext.subMenu(cfg: SubMenuContext.() -> Unit): SubMenu =
//    rootMenu.addSubMenu(this, cfg)

/**
 * create and return a new [MenuItem] using the [MenuItemContext] DSL block
 *
 * Type: Full Block DSL / Inline
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
inline fun MenuContext.menuItemInline(cfg: MenuItemContext.() -> Unit): MenuItem =
    rootMenu.add(this, cfg)

// inline fun MenuContext.subMenuInline(cfg: SubMenuContext.() -> Unit): SubMenu =
//    rootMenu.addSubMenu(this, cfg)

///////////////////////
// Partial Block DSL
///////////////////////

/**
 * create and return a new [MenuItem] using the [MenuItemContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
fun MenuContext.menuItem(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, groupId, itemId, order, title, cfg)

/**
 * create and return a new [MenuItem] using the [MenuItemContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
fun MenuContext.menuItem(
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem = rootMenu.add(this, title, cfg)

/**
 * create and return a new [SubMenu] using the [SubMenuContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
fun MenuContext.submenu(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, groupId, itemId, order, title, cfg)

/**
 * create and return a new [SubMenu] using the [SubMenuContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [MenuContext]
 *
 * @param cfg DSL block
 */
fun MenuContext.submenu(
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    rootMenu.addSubMenu(this, title, cfg)
