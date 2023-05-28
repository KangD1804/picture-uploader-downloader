import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import axios from 'axios';

const DOCUMENT_API = 'http://localhost:8080/document'
// Action types
const SET_DOCUMENT_TEXT = 'SET_DOCUMENT_TEXT';
const FETCH_DOCUMENT_TEXT = 'FETCH_DOCUMENT_TEXT';

// Action creators
export const setDocumentText = (text) => ({
    type: SET_DOCUMENT_TEXT,
    payload: text
});

export const fetchDocumentText = () => {
    return (dispatch) => {
        dispatch({ type: FETCH_DOCUMENT_TEXT });
        return axios.get(DOCUMENT_API) // Change the URL if necessary
            .then((response) => {
                dispatch(setDocumentText(response.data));
            })
            .catch((error) => {
                console.error('Error fetching document text:', error);
            });
    };
};

// Reducer
const initialState = {
    documentText: '',
    loading: false
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_DOCUMENT_TEXT:
            return {
                ...state,
                documentText: action.payload,
                loading: false
            };
        case FETCH_DOCUMENT_TEXT:
            return {
                ...state,
                loading: true
            };
        default:
            return state;
    }
};

const store = createStore(reducer, applyMiddleware(thunk));

export default store;
