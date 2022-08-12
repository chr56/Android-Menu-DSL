# Android-Menu-DSL

[![](https://jitpack.io/v/chr56/Android-Menu-DSL.svg)](https://jitpack.io/#chr56/Android-Menu-DSL)

An kotlin Android library to define menu items using kotlin DSL.

### Usage

```groovy
dependencies {
    implementation("com.github.chr56:Android-Menu-DSL:Tag")
}
```

Please also add jitpack to repositories list.

#### Example

```kotlin
 override fun onCreateOptionsMenu(menu: Menu): Boolean {
    attach(from = menu) {
        menuItem {
            itemId = R.id.setting
            title = getString(R.string.title_setting)
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_settings_white_24dp)
            showAsActionFlag = SHOW_AS_ACTION_NEVER
        }
        menuItem {
            itemId = R.id.about
            titleRes(R.string.title_about,this@MainActivity)
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_info_outline_white_24dp)
            showAsActionFlag = SHOW_AS_ACTION_NEVER
        }
    }
}
```

### Changelog

**0.0.4**

- remove deprecated method
- return MenuItem for `MenuContext.menuItem()`
- return Submenu for `MenuContext.submenu()`

**0.0.3**

- rename function `menu` to `attach`
- require `menuContext` for all Menu extension functions

**0.0.2**

- add `onClick { }` in MenuItemCfg
- implement SubMenu (Experiental)
