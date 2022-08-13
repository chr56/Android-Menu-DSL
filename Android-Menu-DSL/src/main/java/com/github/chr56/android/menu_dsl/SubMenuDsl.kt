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
@file:JvmName("SubMenuDSL")

package com.github.chr56.android.menu_dsl

import android.view.MenuItem
import android.view.SubMenu
import com.github.chr56.android.menu_extension.add
import com.github.chr56.android.menu_extension.addSubMenu
import com.github.chr56.android.menu_model.MenuItemContext
import com.github.chr56.android.menu_model.SubMenuContext

// //////////////////////////////
// Receiver: SubMenuContext (submenu)
// //////////////////////////////

// ////////////////
// Full Block DSL
// ////////////////

/**
 * create and return a new [MenuItem] in SubMenu using the [MenuItemContext] DSL block
 *
 * Type: Full Block DSL
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
fun SubMenuContext.menuItem(cfg: MenuItemContext.() -> Unit): MenuItem =
    currentMenu.add(menuContext, cfg)

// fun SubMenuContext.subMenu(cfg: SubMenuContext.() -> Unit): SubMenu =
//    currentMenu.addSubMenu(MenuContext, cfg)

/**
 * create and return a new [MenuItem] in SubMenu using the [MenuItemContext] DSL block
 *
 * Type: Full Block DSL / Inline
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
inline fun SubMenuContext.menuItemInline(cfg: MenuItemContext.() -> Unit): MenuItem =
    currentMenu.add(menuContext, cfg)

// inline fun SubMenuContext.subMenuInline(cfg: SubMenuContext.() -> Unit): SubMenu =
//    currentMenu.addSubMenu(MenuContext, cfg)

// //////////////////
// Partial Block DSL
// //////////////////

/**
 * create and return a new [MenuItem] in SubMenu using the [MenuItemContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
fun SubMenuContext.menuItem(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem =
    currentMenu.add(menuContext, groupId, itemId, order, title, cfg)

/**
 * create and return a new [MenuItem] in SubMenu using the [MenuItemContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
fun SubMenuContext.menuItem(
    title: CharSequence,
    cfg: MenuItemContext.() -> Unit
): MenuItem =
    currentMenu.add(menuContext, title, cfg)

/**
 * create and return a new [SubMenu] in SubMenu using the [SubMenuContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
fun SubMenuContext.subMenu(
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    currentMenu.addSubMenu(menuContext, groupId, itemId, order, title, cfg)

/**
 * create and return a new [SubMenu] in SubMenu using the [SubMenuContext] DSL block
 *
 * Type: Partial Block DSL
 * @receiver [SubMenuContext]
 *
 * @param cfg DSL block
 */
fun SubMenuContext.subMenu(
    title: CharSequence,
    cfg: SubMenuContext.() -> Unit
): SubMenu =
    currentMenu.addSubMenu(menuContext, title, cfg)
