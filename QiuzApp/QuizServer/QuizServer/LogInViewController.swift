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
    
    var CategoryArray = [String]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadCategoies()
        // Do any additional setup after loading the view.
        navigationItem.hidesBackButton = true
        print("login view load")
        let currentUser=PFUser.currentUser()
        if currentUser != nil{
            print("NOT nil")
            let createQuizViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("CreateQuiz") as? CreateQuizViewController
            
            //createQuizViewControllerObj!.setCategoryies(CategoryArray)
            createQuizViewControllerObj?.navigationItem.hidesBackButton = true
            self.navigationController?.pushViewController(createQuizViewControllerObj!, animated: true)
            
            let currentUserName = currentUser?.username;
            let welcomeUser:String = currentUserName!
            print("user logged in already \(currentUserName)")
            let alertController = UIAlertController(title: "Login", message: "Welcome \(welcomeUser)", preferredStyle: .Alert)
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
        var isLoginSucess = false
        print(UserNameText.text!)
         print(PasswordText.text!)
        PFUser.logInWithUsernameInBackground(UserNameText.text!, password: PasswordText.text!) { (user, error) -> Void in
                print(" inside")
                if error == nil{
                     print("nil")
                if let logedInUser = user {
                    
                    isLoginSucess = true
                    print("run")
                    let createQuizControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("CreateQuiz") as? CreateQuizViewController
                    //createQuizControllerObj!.setCategoryies(self.CategoryArray)
                    self.navigationController?.pushViewController(createQuizControllerObj!, animated: true)
                    
                }else{
                    print(" else logedInUser = user")
                   
                                   }
                }else{
                     print("error not nil")
                    print("error ==>\(error?.description)")

                }
                
        }
        print("isLoginSucess \(isLoginSucess)")
        
        
    }


    
    func loadCategoies()         {
        print("loadCategoies")
        let query=PFQuery(className: "Questions")
        query.selectKeys(["category"])
        
        print("loadCategoies 1")
        // query.findObjects()
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil{
                print("Error != nil")
                if let returnedObjects = objects{
                    for object in returnedObjects{
                        print("zzzz = loadCategoies")
                        if( self.CategoryArray.contains(object["category"] as! String )){
                            
                        }else{
                            self.CategoryArray.append(object["category"] as! String )
                            print("loadCategoies")
                        }
                    }
                }else{
                    print("Failed retrive to Categories ")
                }
                
            }else{
                print("Error occured while retriving Categories")
            }
        }
        //print("End loadCategoies")
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