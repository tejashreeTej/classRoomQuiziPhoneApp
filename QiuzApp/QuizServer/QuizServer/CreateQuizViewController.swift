//
//  CreateQuizViewController.swift
//  QuizServer
//
//  Created by Abhijeet Bhoite on 07/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class CreateQuizViewController: UIViewController, UIPickerViewDelegate,UIPickerViewDataSource {
    
    @IBOutlet weak var CategoryPicker: UIPickerView!
    @IBOutlet weak var DurationPicker: UIPickerView!
    
    @IBOutlet weak var totalQueText: UITextField!
    //var CategoryArray = [String]()
    var CategoryArray = ["Java","swift","HTML","JSP"]
    
    var DurationArray = ["1 min","10 min","15 min","20 min","30 min","90 min"]
    
    var CategorySelected:Int = 0
    var DurationSelected:Int=0
    var totalQestion:Int=0
    
    func loadCategoies()         {
        print("loadCategoies")
        let query=PFQuery(className: "Questions")
        query.selectKeys(["category"])
        print("loadCategoies")
        // query.findObjects()
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil{
                print("error == nil")
                if let _ = objects{
                    print("let _ = objects")
                    for object in objects!{
                        if( self.CategoryArray.contains(object["category"] as! String )){
                            
                        }else{
                            self.CategoryArray.append(object["category"] as! String )
                            print(object["category"] as! String  )
                        }
                    }
                }else{
                    
                }
                
            }else{
                print("Failed retrive to Questions ")
            }
        }
        //print("End loadCategoies")
    }
    
    override func viewDidLoad(){
        //loadCategoies()
        //print("a")
        super.viewDidLoad()
        //print("b")
        // Do any additional setup after loading the view.
        navigationItem.hidesBackButton = true
        CategoryPicker.delegate = self
        CategoryPicker.dataSource = self
        
        DurationPicker.delegate = self
        DurationPicker.dataSource = self
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func setCategoryies(paramCategoryArray:[String]){
        CategoryArray = paramCategoryArray
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        //print("c")
        if pickerView == CategoryPicker{
            return CategoryArray[row]
        }else{
            return DurationArray[row]
        }
        
       // return CategoryArray[row] //--will never execute
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        //print("d")
        if pickerView == CategoryPicker{
           // print("CategoryArray.count = \(CategoryArray.count)")
            return CategoryArray.count
        }else{
           // print("DurationArray = \(DurationArray.count)")
            return DurationArray.count
        }
        
        //return CategoryArray.count //--will never execute
    }
    
    
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        // print("e")
        //print(CategorySelected)
        if pickerView == CategoryPicker{
            CategorySelected = row
        }else{
            DurationSelected = row
        }
        
    }
    
    
    @IBAction func createQuiz(sender: UIButton) {
        print("createQuiz")
        var retunedObjects = []
        print("login view load")
        let currentUser=PFUser.currentUser()
        if currentUser != nil{
            currentUser?.username as String!
            print("User => \(currentUser?.username as String!)")
        }
        let txtTotelQ:Int? = Int(totalQueText.text!)
        
        totalQestion = txtTotelQ!
        print("createQuiz 1")
        let query=PFQuery(className: "Questions")
        print(" CategoryArray[CategorySelected]")
        print(CategoryArray[CategorySelected])
        query.whereKey("category", equalTo: CategoryArray[CategorySelected] )
        query.limit = totalQestion
        print("totalQestion \(totalQestion)")
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil{
                print("error == nil 1")
                var iQuestionCount = 0
                if objects != nil{
                    retunedObjects = objects!
                        print("error == nil 2 =>\(retunedObjects.count)")
                           for object in retunedObjects{
                          let  object = object as! [NSObject:AnyObject]
                              print("ji")
                            if(iQuestionCount >= self.totalQestion){
                                break
                            }
                            iQuestionCount = iQuestionCount + 1
                            let QuizObject=PFObject(className: "QuizToday")
                            print("ji 2")
                                QuizObject["question"] = object["question"]
                                print("ji 3")
                                QuizObject["Answers"] = object["Answers"]
                              print("ji 4")
                                QuizObject["correctAnswer"] = object["correctAnswer"] 
                            print("jiya")
                                QuizObject.saveInBackgroundWithBlock { (sucessful, error) -> Void in
                                    if sucessful {
                                        print("Sucessfully Created new Quiz")
                                    }else{
                                        print("Sorry")
                                    }
                                    
                                }
                    }
                    }else{
                        print("objects != nil")
                    }
                    
                }else{
                    print("Failed to retrive Questions ")
                }
            }
            
        let alertController = UIAlertController(title: "Login", message: "Creating Quiz", preferredStyle: .Alert)
        self.presentViewController(alertController, animated: true, completion: nil)
        let delay = 5.0 * Double(NSEC_PER_SEC)
        let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
        dispatch_after(time, dispatch_get_main_queue(), {
            alertController.dismissViewControllerAnimated(true, completion: nil)
        })
        
       /* let alert = UIAlertController(title: "Today's Quiz",
            message: "Quiz Created successfully",
            preferredStyle: .Alert)
        let action = UIAlertAction(title: "OK", style: .Default,
            handler: nil)
        alert.addAction(action)
        self.presentViewController(alert, animated: true, completion: nil)
        */
        }
    
    
        @IBAction func removeQuiz(sender: AnyObject) {
            let query=PFQuery(className: "QuizToday")
            query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
                if objects != nil && error == nil{
                    let returnedObjects = objects!
                    for object in returnedObjects {
                        
                        object.deleteInBackgroundWithBlock({ (sucess, error) -> Void in
                            if sucess{
                                print("Succesfull delete")
                            }else{
                                print("Failed to delete")
                            }
                        })
                        
                    }
                }
            }
            
            totalQueText.text = ""
            
            let alertController = UIAlertController(title: "Login", message: "Removing Quiz", preferredStyle: .Alert)
            self.presentViewController(alertController, animated: true, completion: nil)
            let delay = 5.0 * Double(NSEC_PER_SEC)
            let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
            dispatch_after(time, dispatch_get_main_queue(), {
                alertController.dismissViewControllerAnimated(true, completion: nil)
            })
            
            /*let alert = UIAlertController(title: "Today's Quiz",
                message: "Quiz Removed successfully",
                preferredStyle: .Alert)
            let action = UIAlertAction(title: "OK", style: .Default,
                handler: nil)
            alert.addAction(action)
            self.presentViewController(alert, animated: true, completion: nil)*/
        }
    
    
    
    
    @IBAction func txtEditingDone(sender: AnyObject) {
        sender.resignFirstResponder()
    }
    
    
    @IBAction func backgroundTap(sender: AnyObject) {
        totalQueText.resignFirstResponder()
        
    }
    
    
    @IBAction func logOut(sender: UIButton) {
           PFUser.logOut()
        let logInViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("LogIn") as? LogInViewController
        self.navigationController?.pushViewController(logInViewControllerObj!, animated: true)
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
