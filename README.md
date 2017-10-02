# OpenEMS Modbus/TCP-Api Example

This is a simple application that reads data from OpenEMS Modbus/TCP slave. For more information refer to [Modbus/TCP-Api](
https://github.com/OpenEMS/openems/blob/master/doc/modbustcp-api.md)

## System

The system in this example has the following setup:

 - PV-Meter: meter1
 - Grid-Meter: meter0
 - Storage system: ess0

## Configuration

OpenEMS Modbus/TCP-Api is configured as follows:

```
{
	"class": "io.openems.impl.controller.api.modbustcp.ModbusTcpApiController",
	"priority": 150,
	"mapping": {
		"0":"system0/OpenemsVersionMajor",
		"2":"meter1/ActivePowerL1",
		"6":"meter1/ActivePowerL2",
		"10":"meter1/ActivePowerL3",
		"14":"meter0/ActivePowerL1",
		"18":"meter0/ActivePowerL2",
		"22":"meter0/ActivePowerL3",
		"26":"ess0/Soc",
		"30":"ess0/ActivePowerL1",
		"34":"ess0/ActivePowerL2",
		"38":"ess0/ActivePowerL3"
	}
}
```
