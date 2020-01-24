package com.polygonetic.process;

import org.apache.commons.exec.LogOutputStream;

class MyLogOutputStream extends LogOutputStream {

	private ProcessExecutorHandler handler;
	private boolean forewordToStandardOutput;

	MyLogOutputStream(final ProcessExecutorHandler handler, final boolean forewordToStandardOutput) {
		this.handler = handler;
		this.forewordToStandardOutput = forewordToStandardOutput;
	}

	@Override
	protected void processLine(final String line, int level) {
		if (forewordToStandardOutput) {
			handler.onStandardOutput(line);
		} else {
			handler.onStandardError(line);
		}
	}
}
