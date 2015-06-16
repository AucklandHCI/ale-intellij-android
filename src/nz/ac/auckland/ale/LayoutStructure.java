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
import nz.ac.auckland.alm.XTab;
import nz.ac.auckland.alm.YTab;
import nz.ac.auckland.linsolve.Variable;

import java.util.*;


public class LayoutStructure {
  final private LayoutSpec layoutSpec;
  final Map<XTab, Edge> xTabEdgeMap = new HashMap<XTab, Edge>();
  final Map<YTab, Edge> yTabEdgeMap = new HashMap<YTab, Edge>();
  final List<XTab> xTabs;
  final List<YTab> yTabs;
  final List<Area> areas;

  public LayoutStructure(LayoutSpec layoutSpec, Area removedArea) {
    this.layoutSpec = layoutSpec;
    this.areas = new ArrayList<Area>(layoutSpec.getAreas());
    if (removedArea != null)
      this.areas.remove(removedArea);
    Edge.fillEdges(layoutSpec, xTabEdgeMap, yTabEdgeMap, removedArea);

    xTabs = new ArrayList<XTab>(xTabEdgeMap.keySet());
    yTabs = new ArrayList<YTab>(yTabEdgeMap.keySet());

    Comparator<Variable> tabComparator = new Comparator<Variable>() {
      @Override
      public int compare(Variable variable, Variable variable2) {
        return variable.getValue() < variable2.getValue() ? -1 : 1;
      }
    };
    Collections.sort(xTabs, tabComparator);
    Collections.sort(yTabs, tabComparator);
  }

  public LayoutSpec getLayoutSpec() {
    return layoutSpec;
  }

  public List<Area> getAreas() {
    return areas;
  }

  public Map<XTab, Edge> getXTabEdges() {
    return xTabEdgeMap;
  }

  public Map<YTab, Edge> getYTabEdges() {
    return yTabEdgeMap;
  }

  public XTab findTabLeftOf(double x) {
    return findFirstSmallerTab(x, xTabs);
  }

  public XTab findTabRightOf(double x) {
    return findFirstLargerTab(x, xTabs);
  }

  public YTab findTabAbove(double y) {
    return findFirstSmallerTab(y, yTabs);
  }

  public YTab findTabBellow(double y) {
    return findFirstLargerTab(y, yTabs);
  }

  private <Tab extends Variable> Tab findFirstLargerTab(double value, List<Tab> tabs) {
    for (int i = 0; i < tabs.size(); i++) {
      Tab tab = tabs.get(i);
      if (tab.getValue() > value)
        return tab;
    }
    return null;
  }

  private <Tab extends Variable> Tab findFirstSmallerTab(double value, List<Tab> tabs) {
    for (int i = tabs.size() - 1; i >= 0; i--) {
      Tab tab = tabs.get(i);
      if (tab.getValue() < value)
        return tab;
    }
    return null;
  }
}
