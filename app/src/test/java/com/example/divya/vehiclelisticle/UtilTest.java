package com.example.divya.vehiclelisticle;

import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import com.example.divya.vehiclelisticle.utils.Util;

import org.junit.Test;

import java.net.InetAddress;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class UtilTest extends InstrumentationTestCase {


    @Test
    public void testisConnected() {

        final String url = "www.google.com";
        RenamingDelegatingContext context = new RenamingDelegatingContext(getInstrumentation().getTargetContext(), "test_");
        assertEquals(true, Util.isConnected(context));
    }
}
