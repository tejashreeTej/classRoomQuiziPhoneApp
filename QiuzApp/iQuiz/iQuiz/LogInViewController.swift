//
//  LogInViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 03/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse
class LogInViewController: UIViewController {
    
    @IBOutlet weak var UserNameText: UITextField!
    @IBOutlet weak var PasswordText: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        navigationItem.hidesBackButton = true
        
        var quizesViewController:TrailQuizViewController!
        quizesViewController =
            storyboard?.instantiateViewControllerWithIdentifier("Quiz")
            as! TrailQuizViewController
       // quizesViewController.loadTableView()

        print("login view load")
        let currentUser=PFUser.currentUser()
        if currentUser != nil{
            print("NOT nil")
            let mainViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Main") as? MainToolBarViewController
            mainViewControllerObj?.navigationItem.hidesBackButton = true
            
            //mainViewControllerObj!.loadMainTableView()
            
            self.navigationController?.pushViewController(mainViewControllerObj!, animated: true)
            
            let currentUserName = currentUser?.username;
            print("user logged in already \(currentUserName)")
            let alertController = UIAlertController(title: "Login", message: "Welcome \(currentUserName)", preferredStyle: .Alert)
            self.presentViewController(alertController, animated: true, completion: nil)
            let delay = 5.0 * Double(NSEC_PER_SEC)
            let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
            dispatch_after(time, dispatch_get_main_queue(), {
                alertController.dismissViewControllerAnimated(true, completion: nil)
            })
            
            
        }else{
            print(" nil")
            
        }
        print("DONE login view load")
    }
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func signInBtnClicked(sender: AnyObject) {
        print("Sing in clicked")
        
        
        // Not to load yellow view: lazy loading to keep memory overhead down.
        
        

        
        PFUser.logInWithUsernameInBackground(
            UserNameText.text!, password: PasswordText.text!) { (user, error) -> Void in
                if let _ = user {
                    /*let alert = UIAlertController(title: "Login",
                    message: "You are logged in successfully",
                    preferredStyle: .Alert)
                    let action = UIAlertAction(title: "OK", style: .Default,
                    handler: nil)
                    alert.addAction(action)
                    self.presentViewController(alert, animated: true, completion: nil)
                    */
                   // ScoreReportViewController.loadScoreTableView(ScoreReportViewController)
                    
                    //let  scoreReportViewController = self.storyboard?.instantiateViewControllerWithIdentifier("ScoreReport") as?  ScoreReportViewController
                    //scoreReportViewController?.loadScoreTableView()
                    
                    let mainViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Main") as? MainToolBarViewController
                    
                    // mainViewControllerObj!.loadMainTableView()
                    
                    self.navigationController?.pushViewController(mainViewControllerObj!, animated: true)
                    
                    print("error ==>\(error?.description)")
                    
                }else{
                    
                    print("error login")
                    print("error ==>\(error?.description)")
                }
                
                
                print("DONE sign in clicked")
        }
    }
    
    
    
    @IBAction func txtEditingDone(sender: AnyObject) {
        sender.resignFirstResponder()
    }
    
    
    @IBAction func backgroundTap(sender: AnyObject) {
        UserNameText.resignFirstResponder()
        PasswordText.resignFirstResponder()
    }

    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }
    */
    
    
    
}