class Solution {
    public boolean isValid(String s) {
      Deque<Character> ravi =  new ArrayDeque<>();
      
    

      for(int i = 0; i < s.length();i++){
        char ch = s.charAt(i);
        
        if(ch == '('){
            ravi.push(')');
        }else if(ch == '{'){
            ravi.push('}');
        }else if(ch == '['){
            ravi.push(']');
        }else{
            if(ravi.isEmpty()){
                return false;
            }

            char expected = ravi.pop();
            if(expected != ch){
                return false;
            }
        }


      }
      return ravi.isEmpty();
    }
}