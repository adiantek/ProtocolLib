/**
 * (c) 2016 dmulloy2
 */
package com.comphenix.protocol.wrappers;

import static org.junit.Assert.assertTrue;
import net.minecraft.server.v1_9_R1.DataWatcher;
import net.minecraft.server.v1_9_R1.Entity;
import net.minecraft.server.v1_9_R1.EntityLightning;

import org.junit.BeforeClass;
import org.junit.Test;

import com.comphenix.protocol.BukkitInitialization;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject;

/**
 * @author dmulloy2
 */

public class WrappedDataWatcherTest {
	
	@BeforeClass
	public static void prepare() {
		BukkitInitialization.initializePackage();
	}

	@Test
	public void test() {
		Entity entity = new EntityLightning(null, 0, 0, 0, true);
		DataWatcher handle = entity.getDataWatcher();

		WrappedDataWatcher wrapper = new WrappedDataWatcher(handle);

		wrapper.setObject(0, (byte) 1);
		assertTrue(wrapper.getByte(0) == 1);

		Serializer serializer = Registry.get(String.class);
		WrappedDataWatcherObject watcherObject = new WrappedDataWatcherObject(3, serializer);
		wrapper.setObject(watcherObject, "Hiya");

		assertTrue(wrapper.getString(3).equals("Hiya"));
	}
}