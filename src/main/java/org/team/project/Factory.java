public class Factory{
    public Character makeCharacter(int n){
        Character newCharacter= null;
        if(n==1)  
        return new Warrior();
        else if(n==2)
        return new Wizard();
}