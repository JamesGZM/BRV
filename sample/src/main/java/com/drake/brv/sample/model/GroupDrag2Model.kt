/*
 * Copyright (C) 2018 Drake, Inc.
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

package com.drake.brv.sample.model

import com.drake.brv.annotaion.ItemOrientation
import com.drake.brv.item.ItemSwipe

/** 为[Group3Model]添加侧滑功能 */
class GroupDrag2Model(
    override var itemOrientationSwipe: Int = ItemOrientation.HORIZONTAL, // 侧滑方向
) : Group3Model(), ItemSwipe