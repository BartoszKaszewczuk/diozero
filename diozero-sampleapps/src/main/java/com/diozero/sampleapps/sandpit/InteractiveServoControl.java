package com.diozero.sampleapps.sandpit;

/*
 * #%L
 * Device I/O Zero - Sample applications
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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pmw.tinylog.Logger;

import com.diozero.PCA9685;
import com.diozero.internal.spi.PwmOutputDeviceFactoryInterface;
import com.diozero.sandpit.Servo;

public class InteractiveServoControl {
	public static void main(String[] args) {
		if (args.length < 1) {
			Logger.error("Usage: {} <i2c-controller>", InteractiveServoControl.class.getName());
			System.exit(1);
		}
		
		int i2c_controller = Integer.parseInt(args[0]);
		Servo.Trim trim = Servo.Trim.MG996R;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				PCA9685 pca9685 = new PCA9685(i2c_controller, 0x40, 50)) {
			while (true) {
				System.out.print("GPIO ('q' to quit): ");
				System.out.flush();
				String line = br.readLine();
				if (line == null) {
					break;
				}
				line = line.trim();
				if (line.equals("") || line.equals("q")) {
					break;
				}
				try {
					int gpio = Integer.parseInt(line);
					run(br, pca9685, gpio, trim);
				} catch (NumberFormatException e) {
					System.out.println("Invalid integer '" + line + "'");
				}
			}
		} catch (IOException e) {
			Logger.error(e, "Error: {}", e);
		}
	}
	
	private static void run(BufferedReader br, PwmOutputDeviceFactoryInterface deviceFactory, int gpio, Servo.Trim trim) throws IOException {
		try (Servo servo = new Servo(deviceFactory, gpio, 1.5f, 50, trim)) {
			while (true) {
				System.out.print("Angle ('q' to quit): ");
				System.out.flush();
				String line = br.readLine();
				if (line == null) {
					break;
				}
				line = line.trim();
				if (line.equals("") || line.equals("q")) {
					break;
				}
				try {
					float angle = Float.parseFloat(line);
					servo.setAngle(angle);
					float pulse_width_ms = servo.getPulseWidthMs();
					float new_angle = servo.getAngle();
					Logger.debug("pulse_width_ms: {}, new_angle: {}", Float.valueOf(pulse_width_ms), Float.valueOf(new_angle));
				} catch (NumberFormatException e) {
					System.out.println("Invalid float '" + line + "'");
				}
			}
		}
	}
}
