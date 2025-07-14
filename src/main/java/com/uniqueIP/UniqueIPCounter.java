package main.java.com.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UniqueIPCounter {

    private static final String FILENAME = "src/main/java/com/ip/ip_addresses.txt";
    private static final long MAX_IP = 1L << 32; // 2^32 IPv4 addresses
    private static final int BITMAP_SIZE = (int) (MAX_IP / 8); // 512 MB = 2^32 bits / 8


    public static long countUniqueIPs(String ipFile) throws IOException {
        byte[] bitmap = new byte[BITMAP_SIZE]; // 512 MB of memory
        long uniqueCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(ipFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                long ipAsLong = parseIpToLong(line);
                int byteIndex = (int) (ipAsLong >>> 3);
                int bitIndex = (int) (ipAsLong & 7); //position of bit

                byte mask = (byte) (1 << bitIndex);
                if ((bitmap[byteIndex] & mask) == 0) {
                    bitmap[byteIndex] |= mask;
                    uniqueCount++;
                }
            }
        }

        return uniqueCount;
    }

    private static long parseIpToLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IP: " + ipAddress);
        }
        return ((long) Integer.parseInt(parts[0]) << 24)
               | ((long) Integer.parseInt(parts[1]) << 16)
               | ((long) Integer.parseInt(parts[2]) << 8)
               | (long) Integer.parseInt(parts[3]);
    }


    public static void main(String[] args) throws IOException {
        System.out.println(countUniqueIPs(FILENAME));

    }

}
