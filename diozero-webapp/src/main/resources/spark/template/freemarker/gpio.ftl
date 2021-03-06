<#--
 #%L
 Device I/O Zero - Web application
 %%
 Copyright (C) 2016 - 2017 mattjlewis
 %%
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 #L%
-->
<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="GPIO">
<#if output??>
	<#assign pin = boardInfo.getByGpioNumber(output.gpio)>
<h2>GPIO #${output.gpio} (${pin.name})</h2>
<p>On? ${output.isOn()?c}</p>
<p>Turn <#if output.isOn()><a href="/gpio/off/${output.gpio}">off</a><#else><a href="/gpio/on/${output.gpio}">on</a></#if>. <a href="/gpio/toggle/${output.gpio}">Toggle</a></p>
<p>Supported modes:</p>
<ul>
	<#list pin.modes as mode><li>${mode}</li></#list>
</ul>
<#else>
<h2>No GPIO</h2>
</#if>

</@layout.masterTemplate>
