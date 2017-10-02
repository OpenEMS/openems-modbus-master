import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.procimg.InputRegister;

public class App {

	// connection parameters
	private final static String HOST = "localhost";
	private final static int PORT = 502;

	// modbus format settings
	private final static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
	private final static int BYTES_INT = 4;
	private final static int BYTES_LONG = 8;

	public static void main(String[] args) throws Exception {
		// connect to modbus slave
		ModbusTCPMaster master = new ModbusTCPMaster(HOST, PORT);
		master.connect();

		// read registers
		InputRegister[] r = master.readInputRegisters(0, 42);

		// print values
		System.out.println("OpenEMS Version Major: " + getInt(r, 0));
		System.out.println("meter1 ActivePowerL1:  " + getLong(r, 2) + " W");
		System.out.println("meter1 ActivePowerL2:  " + getLong(r, 6) + " W");
		System.out.println("meter1 ActivePowerL3:  " + getLong(r, 10) + " W");
		System.out.println("meter0 ActivePowerL1:  " + getLong(r, 14) + " W");
		System.out.println("meter0 ActivePowerL2:  " + getLong(r, 18) + " W");
		System.out.println("meter0 ActivePowerL3:  " + getLong(r, 22) + " W");
		System.out.println("ess0 Soc:              " + getLong(r, 26) + " %");
		System.out.println("ess0 ActivePowerL1:    " + getLong(r, 30) + " W");
		System.out.println("ess0 ActivePowerL2:    " + getLong(r, 34) + " W");
		System.out.println("ess0 ActivePowerL3:    " + getLong(r, 38) + " W");
	}

	/**
	 * @param r
	 *            Result of a call to readInputRegisters
	 * @param from
	 *            Start-Address of this integer
	 * @return the int value
	 */
	private static int getInt(InputRegister[] r, int from) {
		ByteBuffer b = ByteBuffer //
				.allocate(BYTES_INT) //
				.order(BYTE_ORDER);
		for (int i = from; i < from + BYTES_INT / 2; i++) {
			b.put(r[i].toBytes());
		}
		return b.getInt(0);
	}

	/**
	 * @param r
	 *            Result of a call to readInputRegisters
	 * @param from
	 *            Start-Address of this long
	 * @return the long value
	 */
	private static long getLong(InputRegister[] r, int from) {
		ByteBuffer b = ByteBuffer//
				.allocate(BYTES_LONG) //
				.order(BYTE_ORDER);
		for (int i = from; i < from + BYTES_LONG / 2; i++) {
			b.put(r[i].toBytes());
		}
		return b.getLong(0);
	}
}
