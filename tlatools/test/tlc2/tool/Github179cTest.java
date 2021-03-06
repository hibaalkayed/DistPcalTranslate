/*******************************************************************************
 * Copyright (c) 2019 Microsoft Research. All rights reserved. 
 *
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software. 
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Contributors:
 *   Markus Alexander Kuppe - initial API and implementation
 ******************************************************************************/
package tlc2.tool;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tlc2.output.EC;
import tlc2.output.EC.ExitStatus;
import tlc2.tool.liveness.ModelCheckerTestCase;

public class Github179cTest extends ModelCheckerTestCase {

	public Github179cTest() {
		super("Github179c", ExitStatus.FAILURE_SPEC_EVAL);
	}

	@Test
	public void testSpec() {
		assertTrue(recorder.recorded(EC.TLC_FINISHED));

		assertTrue(recorder.recordedWithStringValues(EC.TLC_MODULE_VALUE_JAVA_METHOD_OVERRIDE,
				"public static tlc2.value.impl.Value tlc2.module.TLC.PrintT(tlc2.value.impl.Value)",
				"Attempted to check equality of integer 1 with non-integer:\n" + 
				"{1}"));
		assertTrue(recorder.recordedWithStringValues(EC.TLC_NESTED_EXPRESSION,
				"0. Line 16, column 9 to line 16, column 42 in Github179c\n" + 
				"1. Line 16, column 9 to line 16, column 33 in Github179c\n" + 
				"2. Line 16, column 9 to line 16, column 25 in Github179c\n" + 
				"3. Line 10, column 11 to line 12, column 39 in Github179c\n" + 
				"4. Line 10, column 24 to line 12, column 39 in Github179c\n" + 
				"5. Line 10, column 27 to line 10, column 36 in Github179c\n" + 
				"\n"));
	}
}
