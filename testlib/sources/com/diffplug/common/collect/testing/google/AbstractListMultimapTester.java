/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2016 DiffPlug.
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
package com.diffplug.common.collect.testing.google;

import static com.diffplug.common.collect.testing.Helpers.assertEqualInOrder;

import java.util.Arrays;
import java.util.Collection;

import com.diffplug.common.annotations.GwtCompatible;
import com.diffplug.common.collect.ListMultimap;

/**
 * Superclass for all {@code ListMultimap} testers.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public class AbstractListMultimapTester<K, V>
		extends AbstractMultimapTester<K, V, ListMultimap<K, V>> {

	protected void assertGet(K key, V... values) {
		assertGet(key, Arrays.asList(values));
	}

	protected void assertGet(K key, Collection<V> values) {
		assertEqualInOrder(values, multimap().get(key));

		if (!values.isEmpty()) {
			assertEqualInOrder(values, multimap().asMap().get(key));
			assertFalse(multimap().isEmpty());
		} else {
			assertNull(multimap().asMap().get(key));
		}

		assertEquals(values.size(), multimap().get(key).size());
		assertEquals(values.size() > 0, multimap().containsKey(key));
		assertEquals(values.size() > 0, multimap().keySet().contains(key));
		assertEquals(values.size() > 0, multimap().keys().contains(key));
	}
}