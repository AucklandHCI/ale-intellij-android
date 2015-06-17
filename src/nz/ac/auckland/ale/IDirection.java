/*
 * Copyright (C) 2015 The Android Open Source Project
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
package nz.ac.auckland.ale;

import nz.ac.auckland.alm.Area;
import nz.ac.auckland.alm.LayoutSpec;
import nz.ac.auckland.linsolve.Variable;

import java.util.List;
import java.util.Map;


public interface IDirection {
  <Tab> Edge getEdge(Area area, Map<Tab, Edge> map);
  Variable getTab(Area area);
  Variable getOppositeTab(Area area);
  Variable getOrthogonalTab1(Area area);
  Variable getOrthogonalTab2(Area area);
  Variable getTab(LayoutSpec layoutSpec);
  List<Area> getAreas(Edge edge);
  List<Area> getOppositeAreas(Edge edge);

  Variable createTab();

  void setTab(Area area, Variable tab);
  void setOppositeTab(Area area, Variable tab);
  void setOrthogonalTab1(Area area, Variable tab);
  void setOrthogonalTab2(Area area, Variable tab);
  void setTabs(Area area, Variable tab, Variable orthTab1, Variable oppositeTab, Variable orthTab2);
}