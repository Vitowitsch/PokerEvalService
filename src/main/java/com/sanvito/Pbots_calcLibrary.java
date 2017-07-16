/**
 * Copyright (C) 2012-2013 Owen Derby (ocderby@gmail.com)
 *
 * This file is part of pbots_calc.
 *
 * pbots_calc is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * pbots_calc is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * pbots_calc in a file in teh toplevel directory called "GPLv3".  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.sanvito;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Name;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;

/**
 * Wrapper for library <b>pbots_calc</b><br>
 * This file was autogenerated by
 * <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that
 * <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a
 * few opensource projects.</a>.<br>
 * For help, please visit
 * <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or
 * <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("pbots_calc")
@Runtime(CRuntime.class)
public class Pbots_calcLibrary {
	static {
		BridJ.register();
	}

	/**
	 * Original signature : <code>Results* alloc_results()</code><br>
	 * <i>native declaration : pbots_calc.h:12</i>
	 */
	@SuppressWarnings("deprecation")
	public static Pointer<Pbots_calcResults> alloc_results() {
		return Pointer.pointerToAddress(alloc_results$2(), Pbots_calcResults.class);
	}

	@Ptr
	@Name("alloc_results")
	protected native static long alloc_results$2();

	/**
	 * Original signature : <code>void print_results(Results*)</code><br>
	 * <i>native declaration : pbots_calc.h:13</i>
	 */
	public static void print_results(Pointer<Pbots_calcResults> ResultsPtr1) {
		print_results(Pointer.getPeer(ResultsPtr1));
	}

	protected native static void print_results(@Ptr long ResultsPtr1);

	/**
	 * Original signature : <code>void free_results(Results*)</code><br>
	 * <i>native declaration : pbots_calc.h:14</i>
	 */
	public static void free_results(Pointer<Pbots_calcResults> ResultsPtr1) {
		free_results(Pointer.getPeer(ResultsPtr1));
	}

	protected native static void free_results(@Ptr long ResultsPtr1);

	/**
	 * Original signature :
	 * <code>int calc(const char*, char*, char*, int, Results*)</code><br>
	 * <i>native declaration : pbots_calc.h:15</i>
	 */
	public static int calc(Pointer<Byte> charPtr1, Pointer<Byte> charPtr2, Pointer<Byte> charPtr3, int int1,
			Pointer<Pbots_calcResults> ResultsPtr1) {
		return calc(Pointer.getPeer(charPtr1), Pointer.getPeer(charPtr2), Pointer.getPeer(charPtr3), int1,
				Pointer.getPeer(ResultsPtr1));
	}

	protected native static int calc(@Ptr long charPtr1, @Ptr long charPtr2, @Ptr long charPtr3, int int1,
			@Ptr long ResultsPtr1);
}
