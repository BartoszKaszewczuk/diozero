package com.diozero.internal.provider.test;

/*
 * #%L
 * Device I/O Zero - Core
 * %%
 * Copyright (C) 2016 diozero
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.pmw.tinylog.Logger;

import com.diozero.internal.spi.AbstractDevice;
import com.diozero.internal.spi.DeviceFactoryInterface;
import com.diozero.internal.spi.GpioDigitalOutputDeviceInterface;
import com.diozero.util.RuntimeIOException;

public class TestDigitalOutputDevice extends AbstractDevice implements GpioDigitalOutputDeviceInterface {
	private int gpio;
	private boolean value;

	public TestDigitalOutputDevice(String key, DeviceFactoryInterface deviceFactory, int gpio, boolean initialValue) {
		super(key, deviceFactory);
		
		this.gpio = gpio;
	}

	@Override
	public void closeDevice() {
		Logger.debug("closeDevice()");
	}

	@Override
	public boolean getValue() throws RuntimeIOException {
		return value;
	}

	@Override
	public void setValue(boolean value) throws RuntimeIOException {
		Logger.debug("setValue({})", Boolean.valueOf(value));
		this.value = value;
	}

	@Override
	public int getGpio() {
		return gpio;
	}
}
