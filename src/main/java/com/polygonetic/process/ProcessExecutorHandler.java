package com.polygonetic.process;

interface ProcessExecutorHandler {

	void onStandardOutput(String line);

	void onStandardError(String line);
}
