/*
 * Copyright (c) 2022 chr_56
 */

@file:JvmName("MenuDsl")
package com.github.chr56.android.menu_dsl

import android.view.Menu

fun menu(from: Menu, cfg: MenuContext.() -> Unit) {
    val menuContext = MenuContext(from)
    menuContext.apply(cfg)
}

fun MenuContext.menuItem(cfg: MenuItemCfg.() -> Unit) {
    menu.add(cfg)
}
