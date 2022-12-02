package com.khauminhduy.map.primitives;

import org.eclipse.collections.api.factory.primitive.IntIntMaps;
import org.eclipse.collections.api.factory.primitive.ObjectDoubleMaps;
import org.eclipse.collections.api.map.primitive.ImmutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;

public class PrimitiveMaps {

	public static void main(String[] args) {
		hppcMap();
		eclipseCollectionsMap();
	}

	private static void hppcMap() {

	}

	private static void eclipseCollectionsMap() {
		MutableIntIntMap mutableIntIntMap = IntIntMaps.mutable.empty();
		mutableIntIntMap.addToValue(1, 1);

		ImmutableIntIntMap immutableIntIntMap = IntIntMaps.immutable.empty();

		MutableObjectDoubleMap<String> dObject = ObjectDoubleMaps.mutable.empty();
		dObject.addToValue("price", 150.5);
		dObject.addToValue("quality", 4.4);
		dObject.addToValue("stability", 0.8);
	}

}
