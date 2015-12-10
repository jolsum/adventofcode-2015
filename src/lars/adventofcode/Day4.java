package lars.adventofcode;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Day4 {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		for (int i = 0;; i++) {

			String in = "abcdef" + i;

			String out = DigestUtils.md5Hex(in);

			if (out.startsWith("00000")) {
				System.out.println(i);
				break;
			}

		}

	}

}
