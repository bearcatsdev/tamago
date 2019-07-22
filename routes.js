'use strict';

module.exports = function(app) {
    var todoList = require('./controller');

    app.route('/')
        .get(todoList.notFoundPage);

    app.route('/api/user/newUser')
        .post(todoList.newUser);
        
    app.route('/api/user/login')
        .post(todoList.loginUser);
        
    app.route('/api/user/verifyOtp')
        .post(todoList.verifyOtp);
        
    app.route('/api/user/getChildrenList')
        .post(todoList.getChildrenList);

    app.route('/api/child/newChild')
        .post(todoList.newChild);

    app.route('/api/child/newRelation')
        .post(todoList.newChildRelation); 

    app.route('/api/child/task/newTask')
        .post(todoList.newTask);     
        
    app.route('/api/child/task/getTaskList')
        .post(todoList.getTaskList);   

    // default page if no route found
    app.route('*')
        .get(todoList.notFoundPage);  

    app.route('*')
        .post(todoList.notFoundPage);  
};