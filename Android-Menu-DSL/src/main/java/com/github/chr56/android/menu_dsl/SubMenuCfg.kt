/*
 *
 *  * Copyright (C) 2022 chr_56
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.github.chr56.android.menu_dsl

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

class SubMenuCfg(val menuContext: MenuContext) {

    var itemId: Int = Menu.NONE
    var groupId: Int = Menu.NONE
    var order: Int = Menu.NONE
    var title: CharSequence? = null

    var icon: Drawable? = null
    var headerTitle: CharSequence? = null
    var headerIcon: Drawable? = null
    var headerView: View? = null

    fun headerTitleRes(@StringRes id: Int, context: Context) {
        headerTitle = context.getString(id)
    }
    fun headerIconRes(@DrawableRes id: Int, context: Context) {
        headerIcon = AppCompatResources.getDrawable(context, id)
    }
    fun iconRes(@DrawableRes id: Int, context: Context) {
        icon = AppCompatResources.getDrawable(context, id)
    }
}
