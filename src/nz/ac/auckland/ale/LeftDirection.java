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

/**
 * Created by lec on 14/06/15.
 */
public class LeftDirection implements IDirection {
  @Override
  public <Tab> Edge getEdge(Area area, Map<Tab, Edge> map) {
    return map.get(area.getLeft());
  }

  @Override
  public Variable getTab(Area area) {
    return area.getLeft();
  }

  @Override
  public Variable getTab(LayoutSpec layoutSpec) {
    return layoutSpec.getLeft();
  }

  @Override
  public List<Area> getAreas(Edge edge) {
    return edge.areas1;
  }

  @Override
  public List<Area> getOppositeAreas(Edge edge) {
    return edge.areas2;
  }

}