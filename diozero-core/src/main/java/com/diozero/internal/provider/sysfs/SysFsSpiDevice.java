package com.diozero.internal.provider.sysfs;

/*
 * #%L
 * Device I/O Zero - Core
 * %%
 * Copyright (C) 2016 - 2017 mattjlewis
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


import java.nio.ByteBuffer;

import com.diozero.api.SpiClockMode;
import com.diozero.internal.spi.AbstractDevice;
import com.diozero.internal.spi.DeviceFactoryInterface;
import com.diozero.internal.spi.SpiDeviceInterface;
import com.diozero.util.RuntimeIOException;

public class SysFsSpiDevice extends AbstractDevice implements SpiDeviceInterface {
	private NativeSpiDevice device;
	
	public SysFsSpiDevice(DeviceFactoryInterface deviceFactory, String key, int controller,
			int chipSelect, int frequency, SpiClockMode spiClockMode, boolean lsbFirst) {
		super(key, deviceFactory);
		
		device = new NativeSpiDevice(controller, chipSelect, frequency, spiClockMode, lsbFirst);
	}

	@Override
	protected void closeDevice() throws RuntimeIOException {
		device.close();
	}

	@Override
	public int getController() {
		return device.getController();
	}

	@Override
	public int getChipSelect() {
		return device.getChipSelect();
	}

	@Override
	public void write(ByteBuffer txBuffer) {
		device.write(txBuffer, 0);
	}

	@Override
	public ByteBuffer writeAndRead(ByteBuffer txBuffer) throws RuntimeIOException {
		return device.writeAndRead(txBuffer, 0);
	}
}
