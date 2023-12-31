package com.colddelight.designsystem.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.colddelight.designsystem.icons.iconpack.Close
import com.colddelight.designsystem.icons.iconpack.Day
import com.colddelight.designsystem.icons.iconpack.Delete
import com.colddelight.designsystem.icons.iconpack.Hamburger
import com.colddelight.designsystem.icons.iconpack.Historyselected
import com.colddelight.designsystem.icons.iconpack.Historyunselected
import com.colddelight.designsystem.icons.iconpack.Homeselected
import com.colddelight.designsystem.icons.iconpack.Homeunselected
import com.colddelight.designsystem.icons.iconpack.Minus
import com.colddelight.designsystem.icons.iconpack.Plus
import com.colddelight.designsystem.icons.iconpack.Routineselected
import com.colddelight.designsystem.icons.iconpack.Routineunselected
import com.colddelight.designsystem.icons.iconpack.Topback
import com.colddelight.designsystem.icons.iconpack.Trash
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(
            Delete, Day, Topback, Minus, Historyunselected, Hamburger, Homeselected, Plus, Trash,
            Routineselected, Close, Homeunselected, Routineunselected, Historyselected
        )
        return __AllIcons!!
    }
