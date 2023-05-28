import React from 'react';
import { Provider } from 'react-redux';
import store from './store';
import DocumentViewer from './DocumentViewer';

const App = () => {
    return (
        <Provider store={store}>
            <DocumentViewer />
        </Provider>
    );
};

export default App;
