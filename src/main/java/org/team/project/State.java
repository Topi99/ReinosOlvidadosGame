public interface State {
    // Different states expected
    // HasCard, NoCard, HasPin, NoCash
    void isRunning();   
    void gameOver();   
    //void insertPin(int pinEntered);
    //void requestCash(int cashToWithdraw);
}
