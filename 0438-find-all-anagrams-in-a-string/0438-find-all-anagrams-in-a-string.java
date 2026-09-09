class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ravi = new ArrayList<>();

        // Declare the P and S frequency array
        int[] Pcount = new int[26];
        int[] Scount = new int[26];

        //Filling the P the number of frequency
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            Pcount[c - 'a']++;
        }

        //Slide the Window
        for (int i = 0; i < s.length(); i++) {
            char k = s.charAt(i);
            Scount[k - 'a']++;

            //Remove the character
            if (i >= p.length()) {
                Scount[s.charAt(i - p.length()) - 'a']--;
            }

            //Compare the Charater
            if (Arrays.equals(Pcount, Scount)) {
                ravi.add(i - p.length() + 1);
            }
        }

        return ravi;

    }
}