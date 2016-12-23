/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.testGuiFramework.tests.samples

import com.intellij.ide.ui.UISettings
import com.intellij.testGuiFramework.fixtures.SettingsTreeFixture
import com.intellij.testGuiFramework.impl.GuiTestCase
import org.junit.Ignore
import org.junit.Test


class AddActionToolbarInKotlinTestTest : GuiTestCase() {

  @Test
  @Throws(Exception::class)
    //Mac only test
  fun testAddActionToolbar() {

    //import project
    with(importSimpleApplication()) {

      waitForBackgroundTasksToFinish()
      if (!UISettings.getInstance().SHOW_MAIN_TOOLBAR) invokeMenuPath("View", "Toolbar")
      invokeAction("meta comma")

      with(dialog("Preferences")) {
        SettingsTreeFixture.find(myRobot).select("Appearance & Behavior/Menus and Toolbars")
        jTree("Main Toolbar/Help").clickPath("Main Toolbar/Help")
        button("Add After...").click()

        with(dialog("Choose Actions To Add")) {
          jTree("All Actions/Main menu/File/Print...").clickPath("All Actions/Main menu/File/Print...")
          button("OK").click()
        }
        button("OK").click()
      }
      projectView.selectProjectPane().selectByPath(project.name, "src", "Main.java").click(myRobot)
      actionButton("Print").waitUntilEnabledAndShowing().click()

      with(dialog("Print")) {
        button("Cancel").click()
      }
    }

  }
}
